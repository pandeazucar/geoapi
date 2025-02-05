/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.util.Collection;
import java.util.Collections;
import java.time.temporal.Temporal;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the evaluation method and procedure applied.
 * Data quality evaluation method should be included for each applied {@linkplain Element data quality measure}.
 * It describes, or references documentation describing, the methodology used to apply a data quality measure
 * to the data specified by a {@linkplain DataQuality#getScope() data quality scope}.
 * {@code EvaluationMethod} can be specialized with {@link DataEvaluation} or {@link AggregationDerivation} subtypes.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see Element#getEvaluationMethod()
 *
 * @since 3.1
 */
@UML(identifier="DQ_EvaluationMethod", specification=ISO_19157)
public interface EvaluationMethod {
    /**
     * Type of method used to evaluate quality of the data.
     *
     * @return type of method used to evaluate quality, or {@code null} if none.
     */
    @UML(identifier="evaluationMethodType", obligation=OPTIONAL, specification=ISO_19157)
    default EvaluationMethodType getEvaluationMethodType() {
        return null;
    }

    /**
     * Description of the evaluation method.
     *
     * @return description of the evaluation method, or {@code null} if none.
     */
    @UML(identifier="evaluationMethodDescription", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getEvaluationMethodDescription() {
        return null;
    }

    /**
     * Reference to the procedure information.
     *
     * @return reference to the procedure information, or {@code null} if none.
     */
    @UML(identifier="evaluationProcedure", obligation=OPTIONAL, specification=ISO_19157)
    default Citation getEvaluationProcedure() {
        return null;
    }

    /**
     * Information on documents which are referenced in developing and applying a data quality evaluation method.
     *
     * @return documents referenced in data quality evaluation method.
     *
     * @departure rename
     *   Renamed from {@code "referenceDoc"} to {@code "referenceDocument"}
     *   for avoiding abbreviation (a Java usage).
     */
    @UML(identifier="referenceDoc", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Citation> getReferenceDocuments() {
        return Collections.emptyList();
    }

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The collection size is 1 for a single date, or 2 for a range.
     * Returns an empty collection if this information is not available.
     *
     * @return date or range of dates on which a data quality measure was applied.
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Temporal> getDates() {
        return Collections.emptyList();
    }
}
