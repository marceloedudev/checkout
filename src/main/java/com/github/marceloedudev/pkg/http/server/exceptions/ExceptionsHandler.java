package com.github.marceloedudev.pkg.http.server.exceptions;

import com.github.marceloedudev.domain.errors.base.ErrorMessage;
import com.github.marceloedudev.domain.errors.http.BadRequestException;
import com.github.marceloedudev.domain.errors.http.HttpException;
import com.github.marceloedudev.domain.errors.http.NotFoundException;
import com.github.marceloedudev.pkg.logger.LoggerAdapter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionsHandler implements ExceptionMapper<Exception> {

    private final LoggerAdapter log = LoggerAdapter.getLogger(ExceptionsHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
            int statusCode = Response.Status.FORBIDDEN.getStatusCode();
            return Response
                    .fromResponse(originalErrorResponse)
                    .entity(new ErrorMessage(originalErrorResponse.getStatusInfo().getReasonPhrase(), statusCode))
                    .status(statusCode)
                    .build();
        }
        else if (exception instanceof IllegalArgumentException) {
            int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
            return Response.status(statusCode)
                    .entity(new ErrorMessage(exception.getMessage(), statusCode))
                    .build();
        }
        else if (exception instanceof BadRequestException) {
            int statusCode = Response.Status.BAD_REQUEST.getStatusCode();
            return Response.status(statusCode)
                    .entity(new ErrorMessage(((HttpException) exception).getMessages(), statusCode))
                    .build();
        }
        else if (exception instanceof NotFoundException) {
            int statusCode = Response.Status.NOT_FOUND.getStatusCode();
            return Response.status(statusCode)
                    .entity(new ErrorMessage(((HttpException) exception).getMessages(), statusCode))
                    .build();
        }
        log.error(exception.toString());
        int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        return Response.status(statusCode)
                .entity(new ErrorMessage("Internal Server Error", statusCode))
                .build();
    }

}
