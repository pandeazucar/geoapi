/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2016-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.referencing.gazetteer;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.coordinate.Position;
import org.opengis.metadata.extent.TemporalExtent;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.citation.Party;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifiable geographic place.
 * Properties of location instances are described by {@link LocationType}.
 * The minimum set of attributes of each location instance is:
 * <ul>
 *   <li><b>geographic identifier</b> (the value, for example a name or code)</li>
 *   <li><b>geographic extent</b> (the position of the identified thing)</li>
 *   <li><b>administrator</b> (who is responsible for this identifier)</li>
 *   <li><b>location type</b> (which specifies the nature of the identifier and its associated geographic location)</li>
 * </ul>
 *
 * The following may also be recorded:
 * <ul>
 *   <li><b>temporal extent</b></li>
 *   <li><b>alternative geographic identifier</b></li>
 *   <li><b>position</b> (mandatory if the geographic identifier contains insufficient information to identify location)</li>
 *   <li><b>parent location instance</b></li>
 *   <li><b>child location instance</b></li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see LocationType
 */
@UML(identifier="SI_LocationInstance", specification=ISO_19112)
public interface Location {
    /**
     * Unique identifier for the location instance. The methods of identifying locations is specified by the
     * {@linkplain LocationType#getIdentifications() location type identifications}.
     *
     * <div class="note"><b>Examples:</b>
     * if {@link LocationType#getIdentifications()} contain “name”, then geographic identifiers may be country
     * names like “Japan” or “France”, or places like “Eiffel Tower”. If location type identifications contain
     * “code”, then geographic identifiers may be “SW1P 3AD” postcode.
     * </div>
     *
     * In order to ensure that a geographic identifier is unique within a wider geographic domain,
     * the geographic identifier may need to include an identifier of an instance of a parent location type,
     * for example “Paris, Texas”.
     *
     * @return unique identifier for the location instance.
     *
     * @see LocationType#getIdentifications()
     */
    @UML(identifier="geographicIdentifier", obligation=MANDATORY, specification=ISO_19112)
    InternationalString getGeographicIdentifier();

    /**
     * Other identifier(s) for the location instance.
     *
     * @return other identifier(s) for the location instance, or an empty collection if none.
     */
    @UML(identifier="alternativeGeographicIdentifier", obligation=OPTIONAL, specification=ISO_19112)
    Collection<? extends InternationalString> getAlternativeGeographicIdentifiers();

    /**
     * Date of creation of this version of the location instance.
     *
     * @return date of creation of this version of the location instance, or {@code null} if none.
     */
    @UML(identifier="temporalExtent", obligation=OPTIONAL, specification=ISO_19112)
    TemporalExtent getTemporalExtent();

    /**
     * Description of the location instance.
     * The geographic extent shall be defined in one of the following ways:
     * <ul>
     *   <li>As a collection of smaller geographic features.
     *       Example: the European Union, defined by its constituent countries;</li>
     *   <li>By a {@linkplain org.opengis.metadata.extent.BoundingPolygon bounding polygon},
     *       described by either of the following:
     *     <ul>
     *       <li>As a closed set of boundary segments (each defined by one or more geographic features).
     *           Example: a block defined by the bounding streets.</li>
     *       <li>By a set of coordinates. Example: a land parcel defined by the coordinates of its boundary.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return description of the location instance.
     *
     * @see org.opengis.metadata.extent.GeographicDescription
     * @see org.opengis.metadata.extent.GeographicBoundingBox
     * @see org.opengis.metadata.extent.BoundingPolygon
     */
    @UML(identifier="geographicExtent", obligation=MANDATORY, specification=ISO_19112)
    GeographicExtent getGeographicExtent();

    /**
     * Returns an envelope that encompass the location. This property is partially redundant with
     * {@link #getGeographicExtent()}, except that this method allows envelopes in projected CRS.
     *
     * @return envelope that encompass the location, or {@code null} if none.
     *
     * @departure extension
     *   This method has been added because <code>getGeographicExtent()</code> does not provide an easy
     *   way to get the spatial extent in location "native" coordinate reference system (CRS) if that
     *   CRS is not geographic. For example if the location provides coordinates in a projected CRS,
     *   then its envelope cannot be represented directly in a <code>GeographicBoundingBox</code>.
     */
    Envelope getEnvelope();

    /**
     * Coordinates of a representative point for the location instance.
     * An example of the position is the coordinates of the centroid of the location instance.
     * This provides a linking mechanism to spatial referencing by coordinates.
     *
     * <p>Position must be recorded if the geographic identifier contains insufficient information
     * to identify location.</p>
     *
     * @departure generalization
     *   ISO 19112 declares the <code>GM_Point</code> type. GeoAPI uses the <code>Position</code> union
     *   for allowing the use of either <code>GM_Point</code> or <code>DirectPosition</code>.
     *
     * @return coordinates of a representative point for the location instance, or {@code null} if none.
     */
    @UML(identifier="position", obligation=CONDITIONAL, specification=ISO_19112)
    Position getPosition();

    /**
     * A description of the nature of this geographic identifier.
     *
     * @return the nature of the identifier and its associated geographic location.
     */
    @UML(identifier="locationType", obligation=MANDATORY, specification=ISO_19112)
    LocationType getLocationType();

    /**
     * Name of organization responsible for defining the characteristics of the location instance.
     *
     * @return organization responsible for defining the characteristics of the location instance.
     *
     * @see Gazetteer#getCustodian()
     * @see LocationType#getOwner()
     * @see ReferenceSystemUsingIdentifiers#getOverallOwner()
     */
    @UML(identifier="administrator", obligation=MANDATORY, specification=ISO_19112)
    Party getAdministrator();

    /**
     * Location instances of a different location type, for which this location instance is a sub-division.
     *
     * @return parent locations, or an empty collection if none.
     *
     * @see LocationType#getParents()
     */
    @UML(identifier="parent", obligation=OPTIONAL, specification=ISO_19112)
    Collection<? extends Location> getParents();

    /**
     * Location instances of a different location type which subdivides this location instance.
     *
     * @return child locations, or an empty collection if none.
     *
     * @see LocationType#getChildren()
     */
    @UML(identifier="child", obligation=OPTIONAL, specification=ISO_19112)
    Collection<? extends Location> getChildren();
}
