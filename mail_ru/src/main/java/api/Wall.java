package api;

import configuration.Configuration;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wall {
    private String postID;
    private Logger logger = Logger.getLogger(Wall.class);
    private HttpClient client = HttpClientBuilder.create().build();

    public void postMessage() {
        try {
            URIBuilder builder = new ApiBuilder.builder("wall.post")
                    .setAccessToken()
                    .setOwnerID()
                    .setMessageText()
                    .build();
            HttpResponse response = getHttpResponse(builder, client);
            Pattern pattern = Pattern.compile("\\d+");
            String postIdResponse = EntityUtils.toString(response.getEntity());
            Matcher matcher = pattern.matcher(postIdResponse);
            while (matcher.find()) {
                postID = matcher.group();
            }
            logger.info("Message is posted");
        } catch (IOException | URISyntaxException exception) {
            logger.error(exception);
        }
    }

    public void editMessage() {
        try {
            URIBuilder builder = new ApiBuilder.builder("wall.edit")
                    .setAccessToken()
                    .setOwnerID()
                    .setEditedMessageText()
                    .setPostID(postID)
                    .build();
            HttpResponse response = getHttpResponse(builder, client);
            logger.info("Message is edited");
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
    }

    public void deleteMessage() {
        try {
            URIBuilder builder = new ApiBuilder.builder("wall.delete")
                    .setAccessToken()
                    .setOwnerID()
                    .setPostID(postID)
                    .build();
            HttpResponse response = getHttpResponse(builder, client);
            logger.info("Message is deleted");
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
    }

    public boolean postIsPresent() {
        String regex = "\"id\":" + postID;
        return findMatchesOnWallByRegEx(regex);
    }

    public boolean postIsEdited() {
        String editedMessage = Configuration.getEditedMessageText();
        String regex = "\"id\":" + postID + ".+?text\":\"" + editedMessage;
        return findMatchesOnWallByRegEx(regex);
    }

    public boolean findMatchesOnWallByRegEx(String regex) {
        HttpClient client = HttpClientBuilder.create().build();
        String postIdsResponse;
        boolean matches = false;
        try {
            URIBuilder builder = new ApiBuilder.builder("wall.get")
                    .setAccessToken()
                    .setOwnerID()
                    .build();
            HttpResponse response = getHttpResponse(builder, client);
            postIdsResponse = EntityUtils.toString(response.getEntity());
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(postIdsResponse);
            matches = matcher.find();
        } catch (IOException | URISyntaxException exception) {
            logger.fatal(exception);
        }
        return matches;
    }

    private HttpResponse getHttpResponse(URIBuilder builder, HttpClient client)
            throws URISyntaxException, IOException {
        HttpGet request = new HttpGet(builder.build());
        return client.execute(request);
    }
}