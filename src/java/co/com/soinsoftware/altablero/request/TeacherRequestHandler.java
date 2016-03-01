/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.LOGGER;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 23/02/2016
 */
@Controller
public class TeacherRequestHandler extends AbstractRequestHandler {

    private static final String TEACHER_EDIT_PAGE = "/admin/profesores/edicion";
    private static final String TEACHER_PAGE = "/admin/profesores";
    private static final String TEACHER_SAVE_PAGE = "/admin/profesores/edicion/guardar";

    private static final String TEACHER_MODEL = "admin/teacher/list";
    private static final String TEACHER_EDIT_MODEL = "admin/teacher/edit";

    @RequestMapping(value = TEACHER_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listTeachers() {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(TEACHER_MODEL);
            this.addTeacherListToModel(model);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = TEACHER_EDIT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editTeacher(@RequestParam(value = USER_ID_REQUEST_PARAM, required = false)
            final Integer idUser) {
        ModelAndView model = null;
        try {
            final UserBO user = this.findUserByIdentifier(idUser);
            model = this.buildEditPageModel(user, false, false, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(null, false, false, true, false);
        }
        return model;
    }

    @RequestMapping(value = TEACHER_SAVE_PAGE, method = RequestMethod.POST)
    public ModelAndView saveInformation(
            @RequestParam(value = USER_ID_REQUEST_PARAM, required = true)
            final Integer idUser,
            @RequestParam(value = DOCUMENT_TYPE_REQUEST_PARAM, required = true)
            final String docType,
            @RequestParam(value = DOCUMENT_NUMBER_REQUEST_PARAM, required = true)
            final String docNumber,
            @RequestParam(value = NAME_REQUEST_PARAM, required = true)
            final String name,
            @RequestParam(value = LAST_NAME_REQUEST_PARAM, required = true)
            final String lastName,
            @RequestParam(value = BORN_DATE_REQUEST_PARAM, required = true)
            final String bornDate,
            @RequestParam(value = ADDRESS_REQUEST_PARAM, required = true)
            final String address,
            @RequestParam(value = PHONE1_REQUEST_PARAM, required = true)
            final String phone1,
            @RequestParam(value = PHONE2_REQUEST_PARAM, required = true)
            final String phone2,
            @RequestParam(value = GENDER_REQUEST_PARAM, required = true)
            final String gender,
            @RequestParam(value = FILE_UPLOAD_REQUEST_PARAM, required = true)
            final MultipartFile file,
            @RequestParam(value = COORDINATOR_REQUEST_PARAM, required = false)
            final String coordinator) {
        ModelAndView model = null;
        try {
            final SchoolBO school = this.schoolController.findByIdentifier(this.getIdSchool());
            final Set<UserTypeBO> userTypeSet = new HashSet<>();
            if (coordinator != null) {
                    userTypeSet.add(this.userTypeController.findBy(UserTypeBO.getCoordinatorCode()));
            }
            userTypeSet.add(this.userTypeController.findBy(UserTypeBO.getTeacherCode()));
            final boolean isValidDocNumber = userController.isValidDocumentNumber(idUser, docNumber);
            UserBO user = this.userController.buildUserBO(idUser, docType, docNumber, name,
                    lastName, bornDate, address, phone1, phone2, gender, file, school, userTypeSet);
            boolean saved = false;
            boolean hasServerErrors = false;
            if (isValidDocNumber) {
                final UserBO savedUser = this.userController.save(user);
                if (savedUser != null) {
                    saved = true;
                    user = savedUser;
                } else {
                    hasServerErrors = true;
                }
            }
            model = this.buildEditPageModel(user, saved, false, hasServerErrors, !isValidDocNumber);
        } catch (IOException | ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(null, false, false, true, false);
        }
        return model;
    }

    private ModelAndView buildEditPageModel(final UserBO user, final boolean wasSaved,
            final boolean wasDeactivated, final boolean hasServerErrors,
            final boolean invalidDocNumber) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(TEACHER_EDIT_MODEL);
            if (user != null) {
                if(user.getPhoto() != null && !user.getPhoto().equals("")) {
                    user.setPhoto(this.userController.getHttpPath(user, this.getIdSchool()));
                }
                if (user.getId() != null && user.getId() > 0) {
                    this.addClassListToModel(model, 0, user.getId(), false);
                    model.addObject(CLASSROOM_PARAMETER, this.getGroupDirectorClassRoom(user));
                }
            }
            model.addObject(USER_PARAMETER, user);
            model.addObject(SAVED_PARAMETER, wasSaved);
            model.addObject(DEACTIVATED_PARAMETER, wasDeactivated);
            model.addObject(HAS_SERVER_ERRORS_PARAMETER, hasServerErrors);
            model.addObject(INVALIDCODE_PARAMETER, invalidDocNumber);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }
    
    private ClassRoomBO getGroupDirectorClassRoom(final UserBO user) throws IOException {
        ClassRoomBO groupDirClassRoom = null;
        final String currentYear = yearController.getCurrentYearString();
        final Set<ClassRoomBO> classRoomSet = this.classRoomController.findClassRooms(
                currentYear, 0, this.getIdSchool());
        if (classRoomSet != null) {
            for (final ClassRoomBO classRoom : classRoomSet) {
                if (classRoom.getUserBO().equals(user)) {
                    groupDirClassRoom = classRoom;
                    break;
                }
            }
        }
        return groupDirClassRoom;
    }
}
