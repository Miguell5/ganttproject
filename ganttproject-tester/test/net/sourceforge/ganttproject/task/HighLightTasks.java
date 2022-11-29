/*
Copyright 2014 BarD Software s.r.o

This file is part of GanttProject, an opensource project management tool.

GanttProject is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

GanttProject is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with GanttProject.  If not, see <http://www.gnu.org/licenses/>.
*/
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
