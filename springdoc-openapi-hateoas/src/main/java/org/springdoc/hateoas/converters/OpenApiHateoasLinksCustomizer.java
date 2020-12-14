package org.springdoc.hateoas.converters;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.core.filter.SpecFilter;
import io.swagger.v3.core.util.AnnotationsUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.hateoas.Link;

/**
 * The type Open api hateoas links customizer.
 * @author bnasslahsen
 */
public class OpenApiHateoasLinksCustomizer extends SpecFilter implements OpenApiCustomizer {

	/**
	 * The Spring doc config properties.
	 */
	private final SpringDocConfigProperties springDocConfigProperties;

	/**
	 * Instantiates a new Open api hateoas links customizer.
	 *
	 * @param springDocConfigProperties the spring doc config properties
	 */
	public OpenApiHateoasLinksCustomizer(SpringDocConfigProperties springDocConfigProperties) {
		this.springDocConfigProperties = springDocConfigProperties;
	}

	@Override
	public void customize(OpenAPI openApi) {
		ResolvedSchema resolvedLinkSchema = ModelConverters.getInstance()
				.resolveAsResolvedSchema(new AnnotatedType(Link.class));
		openApi
				.schema("Link", resolvedLinkSchema.schema)
				.schema("Links", new MapSchema()
						.additionalProperties(new StringSchema())
						.additionalProperties(new ObjectSchema().$ref(AnnotationsUtils.COMPONENTS_REF + "Link")));
		if (springDocConfigProperties.isRemoveBrokenReferenceDefinitions())
			this.removeBrokenReferenceDefinitions(openApi);
	}
}
