package org.example.sogetiautomationtask.ui.models;

import org.example.sogetiautomationtask.ui.utils.Country;
import org.example.sogetiautomationtask.ui.utils.PositionLevel;
import org.example.sogetiautomationtask.ui.utils.PurposeOfContact;

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
