package ua.timonov.aplib.web;

import ua.timonov.aplib.service.SchoolClassService;

/**
 * Created by Alex on 02.03.2017.
 */
public class SchoolClassResource {
    private SchoolClassService schoolClassService;

    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }
}
