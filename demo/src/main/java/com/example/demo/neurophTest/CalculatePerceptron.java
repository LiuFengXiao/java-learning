package com.example.demo.neurophTest;


import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;
/**
 * Created by 阿龙 on 2017/1/24.
 */
public class CalculatePerceptron {
    public static NeuralNetwork myPerceptron=null;

    public void trainRobot(){
        DataSet trainAndSet = new DataSet(2, 1);
        trainAndSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainAndSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{0}));
        trainAndSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{0}));
        trainAndSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));

////        建立OR训练集
//        DataSet trainOrSet = new DataSet(2, 1);
//        trainOrSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
//        trainOrSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
//        trainOrSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
//        trainOrSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));
//
////        建立XOR训练集
//        DataSet trainXorSet = new DataSet(2, 1);
//        trainXorSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
//        trainXorSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
//        trainXorSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
//        trainXorSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

//        建立感知机
        myPerceptron = new Perceptron(2, 1);
//        训练AND集
        myPerceptron.learn(trainAndSet);
        System.out.println("测试感知机AND集训练结果：");
        myPerceptron.save("AND_learn_result.nnet");
        testNeuralNetwork(myPerceptron, trainAndSet);

////        训练OR集
//        myPerceptron.learn(trainOrSet);
//        System.out.println("测试感知机Or集训练结果：");
//        myPerceptron.save("OR_learn_result.nnet");
//        testNeuralNetwork(myPerceptron, trainOrSet);
//
////        训练XOR集
////        由于XOR输入输出情况线性不可分，将无法完成训练
//        myPerceptron.learn(trainXorSet);
//        System.out.println("测试感知机Xor集训练结果：");
//        testNeuralNetwork(myPerceptron, trainXorSet);
    }

    public static void main(String args[]){
        CalculatePerceptron calculatePerceptron= new CalculatePerceptron();
        calculatePerceptron.trainRobot();
        DataSet trainAndSet = new DataSet(2, 1);
        trainAndSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainAndSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{0}));
        trainAndSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{0}));
        trainAndSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{1}));
        CalculatePerceptron.testNeuralNetwork(CalculatePerceptron.myPerceptron,trainAndSet);

    }
    public static void testNeuralNetwork(NeuralNetwork nnet, DataSet tset) {

        for (DataSetRow dataRow : tset.getRows()) {
            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[ ] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
        }
    }

}