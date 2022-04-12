// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2018, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package io.openliberty.deepdive.rest.resources;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// tag::MetricsImport[]
import org.eclipse.microprofile.metrics.annotation.Counted;
// end::MetricsImport[]

@Path("systems")
// tag::InventoryResourceInterface[]
public interface SystemResourceInterface {

    /**
     * This method creates a new inventory from the submitted data (name, time and
     * location) by the user.
     */
    @POST
    @Counted(name = "inventoryCreatedCount",
    absolute = true,
    description = "Number of times a new inventory is created")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    Response addNewInventory(@FormParam("name") String name,
        @FormParam("time") String time, @FormParam("location") String location);

    /**
     * This method updates a new inventory from the submitted data (name, time and
     * location) by the user.
     */
    @PUT
    @Path("{id}")
    @Counted(name = "inventoryUpdatedCount",
    absolute = true,
    description = "Number of times an inventory is updated")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    Response updateInventory(@FormParam("name") String name,
        @FormParam("time") String time, @FormParam("location") String location,
        @PathParam("id") int id);

    /**
     * This method deletes a specific existing/stored inventory
     */
    @DELETE
    @Path("{id}")
    @Counted(name = "inventoryDeleteCount",
    absolute = true,
    description = "Number of times a registry has been deleted")
    @Transactional
    Response deleteInventory(@PathParam("id") int id);

    /**
     * This method returns a specific existing/stored inventory in Json format
     */
    @GET
    @Path("{id}")
    @Counted(name = "inventoryAccessCount",
    absolute = true,
    description = "Number of times a specific inventory is requested")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    JsonObject getInventory(@PathParam("id") int inventoryId);

    /**
     * This method returns the existing/stored inventories in Json format
     */
    @GET
    @Counted(name = "inventorysAccessCount",
    absolute = true,
    description = "Number of times the list of inventories method is requested")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    JsonArray getInventories();
    // tag::InventoryResourceInterface[]
}