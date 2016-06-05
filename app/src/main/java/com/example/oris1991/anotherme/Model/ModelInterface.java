package com.example.oris1991.anotherme.Model;


import com.example.oris1991.anotherme.Model.Entities.SMSOrPopup;
import com.example.oris1991.anotherme.Model.Entities.SharePictureOrText;
import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.Entities.Users;

import java.util.List;

/**
 * Created by eldar on 31/05/2016.
 */
public interface ModelInterface {
    public void addSmsOrPop(SMSOrPopup sp);
    public List<SMSOrPopup> getSmsForPerson(String person);
    public List<SMSOrPopup> getSmsOrPopups();
    public List<SMSOrPopup> getPopupsTemplates();
    public List<SMSOrPopup> getSmsTemplates();
    public void addTask(Task task);
    public void addPic(SharePictureOrText sp);
    public void addUser(Users sp);
    public List<SharePictureOrText> getPicture();
    public List<Users> getUsers();
    public Boolean deleteUser(Users sp);



}
