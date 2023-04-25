package FeatureExtraction;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.TextBlockLiteralExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


/**
 * This class to extract features from a java file
 * Input : Java file with a Single Method (Note: if want to use all the features)
 * Output: FeatureMap
 * */ 
public class FeatureExtractor {

    public class FeatureVisitor extends VoidVisitorAdapter<Void> {

        private FeatureMap featureMap = new FeatureMap();

        /**
         * This method to compute #parameters of a java method
         * @param MethodDeclaration
         * @param Void
         */
        @Override
        public void visit(MethodDeclaration md, Void arg){
            super.visit(md, arg);
            if (md.getBody().isPresent()) {
                // set #parameters of method
                featureMap.setNumOfParameters(md.getParameters().size());
            }
        }

        /**
         * This method to compute #parameters of a java method
         * @param ConstructorDeclaration
         * @param Void
         */
        @Override
        public void visit(ConstructorDeclaration cd, Void arg){
            super.visit(cd, arg);
            featureMap.setNumOfParameters(cd.getParameters().size());
        }

        /**
         * This method to compute #if statements of a java method
         * @param IfStmt
         * @param Void
         */
        @Override
        public void visit(IfStmt ifStmt, Void arg) {
            super.visit(ifStmt, arg);
            featureMap.setNumOfIfStatements(featureMap.getNumOfIfStatements() + 1);
        }

        /**
         * This method to compute # for loops of a java method
         * @param ForStmt
         * @param Void
         */
        @Override
        public void visit(ForStmt forStmt, Void arg) {
            super.visit(forStmt, arg);
            featureMap.setNumOfLoops(featureMap.getNumOfLoops() + 1);
        }

        /**
         * This method to compute # while loops of a java method
         * @param WhileStmt
         * @param Void
         */
        @Override
        public void visit(WhileStmt whileStmt, Void arg) {
            super.visit(whileStmt, arg);
            featureMap.setNumOfLoops(featureMap.getNumOfLoops() + 1);
        }

        /**
         * This method to compute # for each loops of a java method
         * @param ForEachStmt
         * @param Void
         */
        @Override
        public void visit(ForEachStmt forEachStmt, Void arg) {
            super.visit(forEachStmt, arg);
            featureMap.setNumOfLoops(featureMap.getNumOfLoops() + 1);
        }

        /**
         * This method computes # assignment expressions in a java method
         * @param AssignExpr
         * @param Void
         */
        @Override
        public void visit(AssignExpr assignExpr, Void arg) {
            super.visit(assignExpr, arg);
            featureMap.setNumOfAssignExprs(featureMap.getNumOfAssignExprs() + 1);
        }

        /**
         * This method computes # comparisons in a java method
         * @param BinaryExpr
         * @param Void
         */
        @Override
        public void visit(BinaryExpr n, Void arg) {
            super.visit(n, arg);
            if (n.getOperator() == Operator.EQUALS || n.getOperator() == Operator.NOT_EQUALS || n.getOperator() == Operator.LESS || n.getOperator() == Operator.LESS_EQUALS || n.getOperator() == Operator.GREATER || n.getOperator() == Operator.GREATER_EQUALS) {
                featureMap.setNumOfComparisons(featureMap.getNumOfComparisons() + 1);
            }
        }

        /**
         * This method computes # boolean literals in a java method
         * @param BooleanLiteralExpr
         * @param Void
         */
        @Override
        public void visit(BooleanLiteralExpr ble, Void arg) {
            super.visit(ble, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # char literals in a java method
         * @param CHarLiteralExpr
         * @param Void
         */
        @Override
        public void visit(CharLiteralExpr cle, Void arg) {
            super.visit(cle, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # integer literals in a java method
         * @param IntegerLiteralExpr
         * @param Void
         */
        @Override
        public void visit(IntegerLiteralExpr ile, Void arg) {
            super.visit(ile, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # long literals in a java method
         * @param LongLiteralExpr
         * @param Void
         */
        @Override
        public void visit(LongLiteralExpr lle, Void arg) {
            super.visit(lle, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # null literals in a java method
         * @param NullLiteralExpr
         * @param Void
         */
        @Override
        public void visit(NullLiteralExpr nle, Void arg) {
            super.visit(nle, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # string literals in a java method
         * @param StringLiteralExpr
         * @param Void
         */
        @Override
        public void visit(StringLiteralExpr sle, Void arg) {
            super.visit(sle, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # text block literals in a java method
         * @param TextBlockLiteralExpr
         * @param Void
         */
        @Override
        public void visit(TextBlockLiteralExpr tble, Void arg) {
            super.visit(tble, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # double literals in a java method
         * @param DoubleLiteralExpr
         * @param Void
         */
        @Override
        public void visit(DoubleLiteralExpr dle, Void arg) {
            super.visit(dle, arg);
            featureMap.setNumOfLiterals(featureMap.getNumOfLiterals() + 1);
        }

        /**
         * This method computes # parenthesis in a java method
         * Note: This counts the number of parenthesis pairs, not total individual parenthesis
         * @param EnclosedExpr
         * @param Void
         */
        //@Override
        //public void visit(EnclosedExpr enclosedExpr, Void arg) {
        //    super.visit(enclosedExpr, arg);
        //    featureMap.setNumOfParenthesis(featureMap.getNumOfParenthesis() + 1);
        //}

        /**
         * This method to get the computed features
         * @return FeatureMap
         */
        public FeatureMap getFeatures() {
            return featureMap;
        }
    }

    /**
     * Replace all string and character literals with empty string
     */
    public class StringLiteralReplacer extends ModifierVisitor<Void> {

        private final String sReplacement = "";
        private final char cReplacement = Character.MIN_VALUE;
        
        @Override
        public Visitable visit(StringLiteralExpr sle, Void arg) {
            return new StringLiteralExpr(sReplacement);
        }

        @Override
        public Visitable visit(CharLiteralExpr cle, Void arg) {
            return new CharLiteralExpr(cReplacement);
        }
    }

}
