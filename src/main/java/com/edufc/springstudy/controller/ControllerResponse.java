package com.edufc.springstudy.controller;

import java.time.LocalDateTime;

public record ControllerResponse(int status, String message, LocalDateTime date) {

}
