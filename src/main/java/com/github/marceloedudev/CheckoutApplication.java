package com.github.marceloedudev;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Checkout",
                version = "1.0",
                contact = @Contact(
                        name = "Marcelo Eduardo",
                        url = "https://github.com/marceloedudev"
                )
        )
)
public class CheckoutApplication extends Application {
}
