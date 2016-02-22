
package com.mconnect.mobile.imageviewer.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;


@Generated("org.jsonschema2pojo")
public class AlbumResource {

    @SerializedName("data")
    @Expose
    private Album album;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("status")
    @Expose
    private int status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AlbumResource() {
    }

    /**
     * 
     * @param status
     * @param album
     * @param success
     */
    public AlbumResource(Album album, boolean success, int status) {
        this.album = album;
        this.success = success;
        this.status = status;
    }

    /**
     * 
     * @return
     *     The album
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * 
     * @param album
     *     The album
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    public AlbumResource withAlbum(Album album) {
        this.album = album;
        return this;
    }

    /**
     * 
     * @return
     *     The success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AlbumResource withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 
     * @return
     *     The status
     */
    public int getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public AlbumResource withStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "AlbumResource{" +
                "album=" + album +
                ", success=" + success +
                ", status=" + status +
                '}';
    }
}
