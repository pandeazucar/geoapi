/*$************************************************************************************************
 **
 ** $Id: MetadataNameNotFoundException.java 658 2006-02-22 18:09:34 -0700 (Wed, 22 Feb 2006) Desruisseaux $
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Thrown when a requested metadata is not found.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 * @deprecated In favor of {@linkplain Coverage} from ISO 19123.
 * 
 * @see SampleDimension#getMetadataValue
 * @see Coverage#getMetadataValue
 * @see org.opengis.coverage.grid.GridCoverageReader#getMetadataValue
 * @see org.opengis.coverage.processing.GridCoverageProcessor#getMetadataValue
 */
@UML(identifier="CV_MetadataNameNotFound", specification=OGC_01004)
public class MetadataNameNotFoundException extends IllegalArgumentException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 3217010469714161299L;

    /**
     * Creates an exception with no message.
     */
    public MetadataNameNotFoundException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public MetadataNameNotFoundException(String message) {
        super(message);
    }
}