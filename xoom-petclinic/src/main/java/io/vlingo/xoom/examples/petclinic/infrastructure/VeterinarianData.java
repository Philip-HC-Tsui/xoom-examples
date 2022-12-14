package io.vlingo.xoom.examples.petclinic.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vlingo.xoom.examples.petclinic.model.veterinarian.VeterinarianState;

public class VeterinarianData {
  public final String id;
  public final FullNameData name;
  public final ContactInformationData contactInformation;
  public final SpecialtyData specialty;

  public static VeterinarianData from(final VeterinarianState veterinarianState) {
    final FullNameData name = veterinarianState.name != null ? FullNameData.from(veterinarianState.name) : null;
    final ContactInformationData contactInformation = veterinarianState.contactInformation != null ? ContactInformationData.from(veterinarianState.contactInformation) : null;
    final SpecialtyData specialty = veterinarianState.specialty != null ? SpecialtyData.from(veterinarianState.specialty) : null;
    return from(veterinarianState.id, name, contactInformation, specialty);
  }

  @JsonCreator
  public static VeterinarianData from(@JsonProperty("id") final String id,
                                      @JsonProperty("name") final FullNameData name,
                                      @JsonProperty("contactInformation") final ContactInformationData contactInformation,
                                      @JsonProperty("specialty") final SpecialtyData specialty) {
    return new VeterinarianData(id, name, contactInformation, specialty);
  }

  public static List<VeterinarianData> from(final List<VeterinarianState> states) {
    return states.stream().map(VeterinarianData::from).collect(Collectors.toList());
  }

  public static VeterinarianData empty() {
    return from(VeterinarianState.identifiedBy(""));
  }

  private VeterinarianData (final String id, final FullNameData name, final ContactInformationData contactInformation, final SpecialtyData specialty) {
    this.id = id;
    this.name = name;
    this.contactInformation = contactInformation;
    this.specialty = specialty;
  }

}
