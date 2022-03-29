package fr.shcherbakov.hibernate.converter;

import fr.shcherbakov.hibernate.model.Certification;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CertificationAttributeConverter implements AttributeConverter<Certification, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Certification attribute) {
		return attribute != null ? attribute.getKey() : null;
	}

	@Override
	public Certification convertToEntityAttribute(Integer dbData) {
		return Stream.of(Certification.values())
				.filter(certification -> certification.getKey().equals(dbData))
				.findFirst()
				.orElse(null);
	}

}
