package distances.derivative_time_domain.ddtw;

import distances.time_domain.dtw.Dtw;
import evaluation.tuning.ParameterSpace;
import utilities.ArrayUtilities;
import utilities.FilterUtilities;
import weka.core.Instance;
import weka.core.Instances;

import static distances.derivative_time_domain.Derivative.DERIVATIVE_FILTER;

public class CachedDdtw extends Dtw {

    @Override
    public double distance(Instance first,
                           Instance second,
                           final double cutOff) {
        Instances instances = new Instances(first.dataset(), 0);
        instances.add(first);
        instances.add(second);
        try {
            instances = FilterUtilities.filter(instances, DERIVATIVE_FILTER);
            first = instances.get(0);
            second = instances.get(1);
            return super.distance(first, second, cutOff);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static final String NAME = "CDDTW";

    @Override
    public String toString() {
        return NAME;
    }

    public static ParameterSpace warpParameterSpace(Instances instances) {
        ParameterSpace parameterSpace = Dtw.warpParameterSpace(instances);
        parameterSpace.addParameter(DISTANCE_MEASURE_KEY, new String[] {NAME});
        return parameterSpace;
    }

    public static ParameterSpace edParameterSpace() {
        ParameterSpace parameterSpace = Dtw.edParameterSpace();
        parameterSpace.addParameter(DISTANCE_MEASURE_KEY, new String[] {NAME});
        return parameterSpace;
    }

    public static ParameterSpace fullWarpParameterSpace() {
        ParameterSpace parameterSpace = Dtw.fullWarpParameterSpace();
        parameterSpace.addParameter(DISTANCE_MEASURE_KEY, new String[] {NAME});
        return parameterSpace;
    }

    public static ParameterSpace allWarpParameterSpace(Instances instances) {
        ParameterSpace parameterSpace = new ParameterSpace();
        parameterSpace.addParameter(DISTANCE_MEASURE_KEY, new String[] {NAME});
        parameterSpace.addParameter(WARPING_WINDOW_KEY, ArrayUtilities.incrementalRange(0, instances.numAttributes() - 1, 101));
        return parameterSpace;
    }

}