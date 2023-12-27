package com.ssafy.imgMaker22.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ssafy.imgMaker22.model.dto.prompt.ImageRequest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GPT4Config {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String IMAGE_URL = "https://api.openai.com/v1/chat/completions";
    public static final String MODEL = "gpt-4-vision-preview";
    public static final int MAX_TOKENS = 300;

    public static String createRequestParam(String text, List<ImageRequest> urls){

        JsonArray content = new JsonArray();
        JsonObject textJson = new JsonObject();
        textJson.addProperty("type", "text");
        textJson.addProperty("text", text);
        content.add(textJson);

        for(ImageRequest imageRequest : urls){
            JsonObject imageJson = new JsonObject();
            imageJson.addProperty("type", "image_url");
            JsonObject urlJson = new JsonObject();
            urlJson.addProperty("url", imageRequest.getUrl());
            imageJson.add("image_url", urlJson);
            content.add(imageJson);
        }

        JsonObject msg = new JsonObject();
        msg.addProperty("role", "user");
        msg.add("content", content);

        JsonArray messages = new JsonArray();
        messages.add(msg);

        JsonObject body = new JsonObject();
        body.addProperty("model", MODEL);
        body.add("messages", messages);
        body.addProperty("max_tokens", MAX_TOKENS);

        return body.toString();
    }

    public static String createPrompt(String style){
        return "make a prompt for dalli to draw " +  style + " style picture using elements from these pictures.\n" +
                "do not draw picture. just tell me the prompt";
    }
}
