package tsml.classifiers.distance_based.distances.ddtw;
/*

Purpose: // todo - docs - type the purpose of the code here

Contributors: goastler
    
*/

import experiments.data.DatasetLoading;
import tsml.classifiers.distance_based.distances.dtw.DTW;
import tsml.classifiers.distance_based.distances.dtw.DTWDistance;
import tsml.classifiers.distance_based.distances.transformed.TransformedDistanceMeasure;
import tsml.filters.Derivative;
import weka.core.Instances;

public class DDTWDistance extends TransformedDistanceMeasure implements DTW {

    private final DTW dtw;

    public DDTWDistance() {
        super();
        dtw = new DTWDistance();
        setTransformer(Derivative.getGlobalCache());
        setDistanceFunction(dtw);
    }

    public DDTWDistance(int warpingWindow) {
        this();
        setWarpingWindow(warpingWindow);
    }

    public static void main(String[] args) throws Exception {
        final Instances[] data = DatasetLoading.sampleGunPoint(0);
        final Instances train = data[0];
        DDTWDistance ddtwDistance = new DDTWDistance();
        ddtwDistance.setInstances(train);
        double distanceA = ddtwDistance.distance(train.get(0), train.get(1));
        double distanceB = ddtwDistance.distance(train.get(0), train.get(1));// it should cache the transform here
        System.out.println();
    }

    @Override
    public int getWarpingWindow() {
        return dtw.getWarpingWindow();
    }

    @Override
    public void setWarpingWindow(int warpingWindow) {
        dtw.setWarpingWindow(warpingWindow);
    }

    @Override
    public double[][] getDistanceMatrix() {
        return dtw.getDistanceMatrix();
    }

    @Override
    public boolean isKeepDistanceMatrix() {
        return dtw.isKeepDistanceMatrix();
    }

    @Override
    public void setKeepDistanceMatrix(boolean state) {
        dtw.setKeepDistanceMatrix(state);
    }

    @Override
    public void cleanDistanceMatrix() {
        dtw.cleanDistanceMatrix();
    }
}
