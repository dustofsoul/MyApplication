package com.example.lhz.myapplication;

import java.util.List;


public class DeliveryMessages {
    private String nu;
    private String com;
    private List<Message> data;
    public static class Message {
        private String time;
        private String context;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }
}
