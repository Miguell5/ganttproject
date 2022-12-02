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

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

/**
 * Tests to simulate import of an excel file with resources
 *
 */
public class TesteImportExcelResources extends TaskTestCase {

    TestSetupHelper helper = new TestSetupHelper();

    @Test
    void testImportExcelResources() {
        HumanResourceManager resourceManager = helper.getResourceManager();
        int numResources = resourceManager.getResources().length();
        int i = 0;
        ExcelImporterSys sys = new ExcelImporterSys("Livro1.xlsx");
        Iterator<Row> it = sys.getRowIterator(0);
        while(it.hasNext()){
            i++;
        }
        RoleManager roleManager = helper.getMyRoleManager();
        sys.makeResources(resourceManager, roleManager, 0);
        assert(numResources == i);
    }


}
