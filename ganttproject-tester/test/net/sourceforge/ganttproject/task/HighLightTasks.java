
package net.sourceforge.ganttproject.task.algorithm;

import java.math.BigDecimal;

import net.sourceforge.ganttproject.TestSetupHelper;
import net.sourceforge.ganttproject.TestSetupHelper.TaskManagerBuilder;
import net.sourceforge.ganttproject.resource.HumanResource;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade;
import net.sourceforge.ganttproject.test.task.TaskTestCase;

/**
 * Tests to simulate Tasks High Lighting when they are complete
 *
 */
public class HighLightTasks extends TaskTestCase {
  private static Color DEFAULT_TASK_COLOR = new Color(140, 182, 206);
  private final ColorOption myDefaultTaskColorOption;
    @Test
    void testHighLightTasks() {
    myDefaultTaskColorOption = new DefaultTaskColorOption(DEFAULT_TASK_COLOR);
    TaskImpl task1 = (TaskImpl) createTask();
    TaskImpl task2 = (TaskImpl) createTask();
    TaskImpl task3 = (TaskImpl) createTask();
    TaskImpl task4 = (TaskImpl) createTask();
    task1.setCompletionPercentage(100);
    task2.setCompletionPercentage(50);
    task3.setCompletionPercentage(100);
    task4.setCompletionPercentage(5);
    task1.setHighlightON();
    task2.setHighlightON();
    task3.setHighlightON();
    task4.setHighlightON();
    assert(task1.getColor() == COLOR.RED);
    assert(task2.getColor() == myDefaultTaskColorOption.getValue());
    assert(task3.getColor() == COLOR.RED);
    assert(task4.getColor() == myDefaultTaskColorOption.getValue());
    }


}
