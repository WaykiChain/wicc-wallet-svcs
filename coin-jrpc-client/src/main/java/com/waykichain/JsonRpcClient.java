package com.waykichain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * <p><b>Created:</b> 16/08/16, 03:42 PM</p>
 * @author <a href="mailto:samuel.quintana@globant.com">samuel</a>
 * @since 1.0
 */
@Slf4j
public class JsonRpcClient {

    @Setter
    private ObjectMapper mapper;
    @Setter
    private String jsonRpcUrl;
    private Integer jsonRpcPort;
    private String rpcUser;
    private String rpcPassword;
    private Boolean isJsonFormat = true;


    private CloseableHttpClient httpClient;

    public JsonRpcClient(String jsonRpcUrl,
                         Integer jsonRpcPort,
                         String rpcUser,
                         String rpcPassword,
                         Boolean isJsonFormat) {
        super();
        this.jsonRpcUrl = jsonRpcUrl;
        this.rpcUser = rpcUser;
        this.rpcPassword = rpcPassword;
        this.mapper = new ObjectMapper();
        this.jsonRpcPort = jsonRpcPort;
        this.isJsonFormat = isJsonFormat;
        if(this.rpcUser != null) {
            AuthScope scope = new AuthScope(jsonRpcUrl, jsonRpcPort);
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(this.rpcUser, this.rpcPassword);
            BasicCredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(scope, credentials);
            this.httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        } else {
            this.httpClient = HttpClients.createDefault();
        }
    }

    public <U> U execute(JsonRpcRequest dataIn, Class<U> classOut) throws IOException {
        String json = this.mapper.writeValueAsString(dataIn);
        HttpPost post = new HttpPost(String.format("http://%s:%d",this.jsonRpcUrl, this.jsonRpcPort));
        StringEntity body = new StringEntity(json);
        post.setEntity(body);
        if(this.isJsonFormat ) {
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
        }
        log.info("request:"+json);
        CloseableHttpResponse response = httpClient.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        log.info("jrpc response: " + sb.toString());
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        return this.mapper.readValue(sb.toString(), classOut);
    }


    public <U> U executeJson(Object dataIn, Class<U> classOut) throws IOException {
        String json = this.mapper.writeValueAsString(dataIn);
        HttpPost post = new HttpPost(jsonRpcUrl);
        StringEntity body = new StringEntity(json);
        post.setEntity(body);
        if(this.isJsonFormat ) {
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
        }


        CloseableHttpResponse response = httpClient.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        return JSON.parseObject(sb.toString(), classOut);
    }



    public <T> T executeGet(Class<T> classOut) throws IOException{

        HttpGet httpGet = new HttpGet(jsonRpcUrl);
        if (this.isJsonFormat) {
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
        }

        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        return JSON.parseObject(sb.toString(), classOut);

    }
}
