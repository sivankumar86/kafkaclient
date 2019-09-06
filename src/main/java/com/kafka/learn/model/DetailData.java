package com.kafka.learn.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailData implements Cloneable {

    private String userId;

    private String visitorId;

    private String type;

    @JsonProperty(value = "Metadata")
    private Metadata metadata;

    private String event;

    private EventData eventData;

    public static class Metadata{

    private  String messageId;
    private  Long sentAt;
    private  Long timestamp;
    private  String receivedAt;
    private String apiKey;
    private String spaceId;
    private String version;

        public Long getSentAt() {
            return sentAt;
        }

        public void setSentAt(Long sentAt) {
            this.sentAt = sentAt;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }



        public String getReceivedAt() {
            return receivedAt;
        }

        public void setReceivedAt(String receivedAt) {
            this.receivedAt = receivedAt;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(String spaceId) {
            this.spaceId = spaceId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

   public static class EventData {
        @JsonProperty(value = "MovieID")
        private String movieID;

        public String getMovieID() {
            return movieID;
        }

        public void setMovieID(String movieID) {
            this.movieID = movieID;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    public Object clone() throws
            CloneNotSupportedException
    {
        return super.clone();
    }
}


