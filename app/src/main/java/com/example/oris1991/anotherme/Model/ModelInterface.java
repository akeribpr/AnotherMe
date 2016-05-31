package com.example.oris1991.anotherme.Model;

import java.util.List;

/**
 * Created by eldar on 31/05/2016.
 */
public interface ModelInterface {
    public void add(SMSOrPopup sp);
    public List<SMSOrPopup> getSmsForPerson(String person);
    public List<SMSOrPopup> getSmsOrPopups();
    public List<SMSOrPopup> getPopupsTemplates();
    public List<SMSOrPopup> getSmsTemplates();


}
