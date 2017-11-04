package enterprise;

import javax.ejb.Stateless;
import javax.inject.Inject;

import model.Task;

@Stateless
public class TaskEJB {
 @Inject Task task;
 
 
}
