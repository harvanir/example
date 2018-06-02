package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthTokenInfo {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("error")
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

    public String getTokenType() {
	return tokenType;
    }

    public void setTokenType(String tokenType) {
	this.tokenType = tokenType;
    }

    public String getRefreshToken() {
	return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
    }

    public int getExpiresIn() {
	return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
	this.expiresIn = expiresIn;
    }

    public String getScope() {
	return scope;
    }

    public void setScope(String scope) {
	this.scope = scope;
    }

    public String getError() {
	return error;
    }

    public void setError(String error) {
	this.error = error;
    }

    public String getErrorDescription() {
	return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
	this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("AuthTokenInfo [accessToken=");
	builder.append(accessToken);
	builder.append(", tokenType=");
	builder.append(tokenType);
	builder.append(", refreshToken=");
	builder.append(refreshToken);
	builder.append(", expiresIn=");
	builder.append(expiresIn);
	builder.append(", scope=");
	builder.append(scope);
	builder.append(", error=");
	builder.append(error);
	builder.append(", errorDescription=");
	builder.append(errorDescription);
	builder.append("]");
	return builder.toString();
    }
}