package com.welovecoding.data.tutorial.entity;

public enum Provider {

    CHANNEL9("Channel9"),
    VIMEO("Vimeo"),
    YOUTUBE("YouTube");

    private final String provider;

    private Provider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public String toString() {
        return provider;
    }

}
