package com.asif.facebook;


import org.springframework.core.io.FileSystemResource;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;


/**
 * <p>
 * Provides service for accessing Facebook's Graph API.
 * </p>
 */
public class FacebookWrapper {

    /**
     * The Facebook template.
     */
    private final Facebook facebook;


    /**
     * Instantiates a new facebook template.
     *
     * @param accessToken the access token
     */
    public FacebookWrapper(String accessToken) {
        this(accessToken, null);
    }


    /**
     * Instantiates a new facebook template.
     *
     * @param accessToken          the access token
     * @param applicationNamespace the application namespace
     */
    public FacebookWrapper(String accessToken, String applicationNamespace) {
        this(accessToken, applicationNamespace, null);
    }


    /**
     * Instantiates a new facebook template.
     *
     * @param accessToken          the access token
     * @param applicationNamespace the application namespace
     * @param appId                the application id
     */
    public FacebookWrapper(String accessToken, String applicationNamespace, String appId) {
        facebook = new FacebookTemplate(accessToken, applicationNamespace, appId);
    }


    /**
     * Retrieves most recent 25 posts from timline
     */
    public PagedList<Post> getTimeline() {
        return this.getTimeline(null);
    }


    /**
     * Retrieves posts from timline
     *
     * @param limit the number of items to limit the list to
     */
    public PagedList<Post> getTimeline(Integer limit) {
        return this.getTimeline(limit, null);
    }


    /**
     * Retrieves posts from timline
     *
     * @param limit the number of items to limit the list to
     * @param since the beginning timestamp bound for time-sensitive content
     */
    public PagedList<Post> getTimeline(Integer limit, Long since) {
        return this.getTimeline(limit, null, since, null, null, null);
    }


    /**
     * Retrieves posts from timline
     *
     * @param limit the number of items to limit the list to
     * @param since the beginning timestamp bound for time-sensitive content, nullable
     * @param until the ending timestamp bound for time-sensitive content
     */
    public PagedList<Post> getTimeline(Integer limit, Long since, Long until) {
        return this.getTimeline(limit, null, since, until, null, null);
    }


    /**
     * Retrieves posts from timline
     *
     * @param limit  the number of items to limit the list to
     * @param offset the offset into the full result list to start this list at
     * @param since  the beginning timestamp bound for time-sensitive content
     * @param until  the ending timestamp bound for time-sensitive content
     */
    public PagedList<Post> getTimeline(Integer limit, Integer offset, Long since, Long until) {
        return this.getTimeline(limit, offset, since, until, null, null);
    }


    /**
     * Retrieves posts from timline
     *
     * @param limit  the number of items to limit the list to
     * @param offset the offset into the full result list to start this list at
     * @param since  the beginning timestamp bound for time-sensitive content
     * @param until  the ending timestamp bound for time-sensitive content
     * @param after  a cursor token that points to the end of the page being returned
     * @param before a cursor token that points to the start of the page being returned
     */
    public PagedList<Post> getTimeline(Integer limit, Integer offset, Long since, Long until, String after, String before) {
        return facebook.feedOperations().getFeed(new PagingParameters(limit, offset, since, until, after, before));
    }


    /**
     * Retrieves friend list of the user
     * The list is ordered by Facebook ID
     */
    public PagedList<User> getFriendListProfiles() {
        return this.getFriendListProfiles("me", null);
    }


    /**
     * Retrieves friend list of the user
     * The list is ordered by Facebook ID.
     *
     * @param limit the number of items to limit the list to
     */
    public PagedList<User> getFriendListProfiles(Integer limit) {
        return this.getFriendListProfiles(limit, null, null, null);
    }


    /**
     * Retrieves friend list of the user specified
     * The list is ordered by Facebook ID.
     *
     * @param userId the user id
     */
    public PagedList<User> getFriendListProfiles(String userId) {
        return this.getFriendListProfiles(userId, null);
    }


    /**
     * Retrieves friend list of the user specified
     * The list is ordered by Facebook ID.
     *
     * @param userId the user id
     * @param limit  the number of items to limit the list to
     */
    public PagedList<User> getFriendListProfiles(String userId, Integer limit) {
        return this.getFriendListProfiles(userId, limit, null, null, null);
    }


    /**
     * Retrieves friend list of the user
     * The list is ordered by Facebook ID.
     *
     * @param limit  the number of items to limit the list to
     * @param offset the offset into the full result list to start this list at
     * @param since  the beginning timestamp bound for time-sensitive content
     * @param until  the ending timestamp bound for time-sensitive content
     */
    public PagedList<User> getFriendListProfiles(Integer limit, Integer offset, Long since, Long until) {
        return this.getFriendListProfiles("me", limit, offset, since, until);
    }


    /**
     * Retrieves friend list of the user specified
     * The list is ordered by Facebook ID.
     *
     * @param userId the user id
     * @param limit  the number of items to limit the list to
     * @param offset the offset into the full result list to start this list at
     * @param since  the beginning timestamp bound for time-sensitive content
     * @param until  the ending timestamp bound for time-sensitive content
     */
    public PagedList<User> getFriendListProfiles(String userId, Integer limit, Integer offset, Long since, Long until) {
        return facebook.friendOperations().getFriendProfiles(userId, new PagingParameters(limit, offset, since, until));
    }


    /**
     * Retrieves the list of the user's friends' IDs.
     */
    public PagedList<String> getFriendListIds() {
        return this.getFriendListIds("me");
    }


    /**
     * Retrieves the list of the user's friends' IDs.
     *
     * @param userId the user ID
     */
    public PagedList<String> getFriendListIds(String userId) {
        return facebook.friendOperations().getFriendIds(userId);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param message the status message
     */
    public String post(String message) {
        return this.post(message, null, null, null);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param message the status message
     * @param tags    the list of profile IDs to be tagged
     */
    public String post(String message, String[] tags) {
        return this.post(message, tags, null);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param message the status message
     * @param tags    the list of profile IDs to be tagged
     * @param placeId the place ID
     */
    public String post(String message, String[] tags, String placeId) {
        return this.post(message, null, null, null);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param message the status message
     * @param linkUrl the url of the link
     */
    public String post(String message, String linkUrl) {
        return this.post(message, linkUrl, null);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param message the status message
     * @param linkUrl the url of the link
     * @param tags    the list of profile IDs to be tagged
     */
    public String post(String message, String linkUrl, String[] tags) {
        return this.post(message, linkUrl, tags, null);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param message the status message
     * @param linkUrl the url of the link
     * @param tags    the list of profile IDs to be tagged
     * @param placeId the place ID
     */
    public String post(String message, String linkUrl, String[] tags, String placeId) {
        return this.post("me", message, linkUrl, null, null, null, tags, placeId, null, null, null);
    }


    /**
     * Updates Status on behalf of the user
     *
     * @param targetFeedId the ID of the owner of the post
     * @param message      the status message
     * @param linkUrl      the url of the link
     * @param name         the name of the link
     * @param caption      the caption of the link
     * @param description  the description of the link
     * @param tags         the list of profile IDs to be tagged
     * @param placeId      the place ID
     * @param picture      the preview image of the link
     * @param allow        list of the profile IDs of the allowed users for this status
     * @param deny         list of the profile IDs of the blocked users for this status
     */
    public String post(String targetFeedId,
                       String message,
                       String linkUrl,
                       String name,
                       String caption,
                       String description,
                       String[] tags,
                       String placeId,
                       String picture,
                       String[] allow,
                       String[] deny) {
        return facebook.feedOperations().post(new PostData(targetFeedId)
                .message(message)
                .link(linkUrl, picture, name, caption, description)
                .tags(tags)
                .place(placeId)
                .allow(allow)
                .deny(deny));
    }

    /**
     * Uploads image on behalf of the user
     *
     * @param path the absolute path of the image
     */
    public String uploadPhoto(String path) {
        return uploadPhoto(path, null);
    }


    /**
     * Uploads image on behalf of the user
     *
     * @param path    the absolute path of the image
     * @param caption the message with the image
     */
    public String uploadPhoto(String path, String caption) {
        return facebook.mediaOperations().postPhoto(new FileSystemResource(path), caption);
    }


    /**
     * Uploads image on behalf of the user
     *
     * @param albumId the album ID, app can upload only in app specified album
     * @param path    the absolute path of the image
     */
    public String uploadPhotoToAlbum(String albumId, String path) {
        return facebook.mediaOperations().postPhoto(albumId, new FileSystemResource(path));
    }
}
