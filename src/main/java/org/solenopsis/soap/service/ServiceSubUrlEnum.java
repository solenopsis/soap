/*
 * Copyright (C) 2017 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.solenopsis.soap.service;

import org.solenopsis.soap.cxf.service.*;

/**
 * The partial URLs for each service
 *
 * @author Scot P. Floess
 */
public enum ServiceSubUrlEnum {
    APEX("services/Soap/s"),
    CUSTOM("services/Soap/class"),
    ENTERPRISE("services/Soap/c"),
    METADATA("services/Soap/m"),
    PARTNER("services/Soap/u"),
    TOOLING("services/Soap/T");

    /**
     * The partial URL.
     */
    private final String partialUrl;

    /**
     * This constructor sets the SFDC web service, port type and partial URL (as defined in the Java doc header).
     *
     * @param webServiceType       the SFDC web service.
     * @param sessionServerFactory is the factory that can compute a server name for a session.
     * @param webServiceSubUrl     the port for the web service.
     */
    private ServiceSubUrlEnum(final String partialUrl) {
        this.partialUrl = partialUrl;
    }

    /**
     * Return our partial URL.
     */
    public String getPartialUrl() {
        return partialUrl;
    }
}
