package org.example.sogetiautomationtask.ui.models;

import org.example.sogetiautomationtask.ui.enums.Country;
import org.example.sogetiautomationtask.ui.enums.PositionLevel;
import org.example.sogetiautomationtask.ui.enums.PurposeOfContact;

public record Message(PurposeOfContact purposeOfContact,
                      String otherPurposeOfContact,
                      String firstName,
                      String lastName,
                      String jobTitle,
                      PositionLevel positionLevel,
                      String email,
                      Country country,
                      String phoneNumber,
                      String company,
                      String message) {
}
