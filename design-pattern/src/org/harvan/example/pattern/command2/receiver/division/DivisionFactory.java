package org.harvan.example.pattern.command2.receiver.division;

import java.util.EnumMap;
import java.util.Map;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class DivisionFactory {
    private static Map<DivisionConstant, IDivision> divisionMap;

    private DivisionFactory() {
	// hidden
    }

    public static IDivision getSuitableDivision(DivisionConstant itDivision) {
	return divisionMap.get(itDivision);
    }

    static {
	divisionMap = new EnumMap<>(DivisionConstant.class);

	divisionMap.put(DivisionConstant.CORE, new CoreDivisionReceiver());
    }
}
