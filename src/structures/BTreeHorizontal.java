package structures;

import java.util.Date;
import java.util.Random;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 31, 2010
 * Time: 9:08:15 PM
 */
public class BTreeHorizontal<T extends Comparable<T>> extends BTreeBase<T> implements iBTree<T> {

    protected BTreeHorizontal() {
    }

    protected BTreeHorizontal(BTree<T> tree) {
        super(tree);
    }

    public BTree<T> join(BTree<T> tbTree) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public BTree<T> removeNode() {
        BTree<T> node = super.getThisBTree();

        if (node == null || node.item == null) return null;

        BTree<T> removedNode = new BTree<T>(node, null, null);

        BTree<T> newNode;
        switch (this.depthOffset(node) == 0 ? 0
                : (0 < this.depthOffset(node) ? 1 : -1)) {
            case -1:
                newNode = this.removeMaxNode(node.getRightChild()); break;
            case 1:
                newNode = this.removeMinNode(node.getLeftChild()); break;
            default:
                newNode = new Random(new Date().getTime()).nextBoolean()
                        ? this.removeMaxNode(node.getRightChild())
                        : this.removeMinNode(node.getLeftChild());
        }

        if (newNode == null) return node;

        node.setNode(newNode, node.getParent(), node.getChildren());

        node.findDepth();

        removedNode.setDepth(0);

        return removedNode;
    }

    public BTree<T> removeNode(BTree<T> node) {
        if (node == null || node.item == null) return null;

        BTree<T> removedNode = new BTree<T>(node, null, null);

        BTree<T> newNode;
        switch (this.depthOffset(node) == 0 ? 0
                : (0 < this.depthOffset(node) ? 1 : -1)) {
            case -1:
                newNode = this.removeMaxNode(node.getRightChild()); break;
            case 1:
                newNode = this.removeMinNode(node.getLeftChild()); break;
            default:
                newNode = new Random(new Date().getTime()).nextBoolean()
                        ? this.removeMaxNode(node.getRightChild())
                        : this.removeMinNode(node.getLeftChild());
        }

        if (newNode == null) return node;

        node.setNode(newNode, node.getParent(), node.getChildren());

        node.findDepth();

        removedNode.setDepth(0);

        return removedNode;
    }

    public void checkSwapSiblings() {

        if (super.getThisBTree().numberOfChildren() < 2) return;

        if (super.getThisBTree().getChildren().get(BTree.getLeftIndex())
                .compareTo(super.getThisBTree().getChildren().get(BTree.getRightIndex())) < 0)
            super.getThisBTree().swapSibling(BTree.getLeftIndex(), BTree.getRightIndex());
    }

    public BTree<T> swimUp(BTree<T> node) {

        if (node == null) return null;

        BTree<T> newNode;
        if ((newNode = super.getThisBTree().getLeftChild()) == null) return null;
        node.removeChild(newNode);
        newNode.setParent(node.getParent());
        node.setNode(node.swimUp(node));
        node.removeChild(newNode);
        node.swimUp(newNode);

        return newNode;
    }

    public BTree<T> getMaxNode(BTree<T> node) {
        if (node == null || node.getLeftChild() == null) return node;

        return getMaxNode(node.getLeftChild());
    }

    public BTree<T> getMinNode(BTree<T> node) {
        if (node == null || node.getRightChild() == null) return node;

        return getMinNode(node.getRightChild());
    }

    public BTree<T> removeMinNode(BTree<T> node) {
        BTree<T> minNode;
        if (node == null || (minNode = getMinNode(node)) == null) return node;

        BTree<T> outNode = new BTree<T>(minNode, null, null);
        BTree<T> pNode = minNode.getParent();
        if (pNode == null) {
            minNode.setItem(null);
            return outNode;
        }
        pNode.removeChild(minNode);
        pNode.setChild(minNode.getLeftChild());

        return outNode;
    }

    public BTree<T> removeMaxNode(BTree<T> node) {
        BTree<T> maxNode;
        if (node == null || (maxNode = getMaxNode(node)) == null) return node;

        BTree<T> outNode = new BTree<T>(maxNode, null, null);
        BTree<T> pNode = maxNode.getParent();
        if (pNode == null) {
            maxNode.setItem(null);
            return outNode;
        }
        BTree<T> rightNode = pNode.getRightChild();
        pNode.removeChild(maxNode);
        if (rightNode == null || maxNode == node) return outNode;
        pNode.setChild(new BTree<T>(pNode, pNode, null));
        pNode.setNode(removeMaxNode(rightNode), pNode.getParent(), pNode.getChildren());

        return outNode;
    }

    public BTree<T> swimUp() {

        BTree<T> newNode;
        if (super.getThisBTree() == null || (newNode = super.getThisBTree().getLeftChild()) == null) return null;
        BTree<T> otherNode = super.getThisBTree().getRightChild();
        super.getThisBTree().removeChild(otherNode);
        newNode.swimUp();
        newNode.setChild(otherNode);
//        newNode.checkSwapSiblings();

        return newNode;
    }

    private long depthOffset(BTree<T> node) {
        if (node == null || node.numberOfChildren() == 0) return 0;
        if (node.numberOfChildren() == 1) return node.getLeftChild().getDepth();

        return node.getLeftChild().getDepth() - node.getRightChild().getDepth();
    }

    public void rotateRight(BTree<T> node) {
        if (node == null || node.numberOfChildren() < 1) return;
        if (depthOffset(node) < 1) return;

        BTree<T> newNode = new BTree<T>(node, null, null);

        if (node.getRightChild() == null)
            node.setChild(newNode);
        else node.getRightChild().add(newNode);

        node.setNode(removeMinNode(node.getLeftChild()), node.getParent(), node.getChildren());

        node.findDepth();
    }

    public void rotateLeft(BTree<T> node) {
        if (node == null || node.numberOfChildren() < 2) return;
        if (-1 < depthOffset(node)) return;

        BTree<T> newNode = new BTree<T>(node, null, null);

        node.getLeftChild().add(newNode);

        node.setNode(removeMaxNode(node.getRightChild()), node.getParent(), node.getChildren());

        node.findDepth();
    }

    private BTree<T> getRandomChild(BTree<T> node) {
        if (node == null) return null;
        if (node.numberOfChildren() < 1) return null;
        if (node.numberOfChildren() == 1) return node.getLeftChild();
        Random rand = new Random(new Date().getTime());
        return (BTree<T>) node.getChild(rand.nextInt() & 1);
    }

    public void add(BTree<T> node) {

        if (node == null) return;
        if (super.getThisBTree().getItem() == null) {
            super.getThisBTree().setItem(node.getItem());
            return;
        }

        switch (super.getThisBTree().compareTo(node)) {
            case -1:
                if (getThisBTree().getLeftChild() == null) getThisBTree().setChild(node);
                else getThisBTree().getLeftChild().add(node);
                break;
            case 0:
            default:
                if (getThisBTree().numberOfChildren() < 1) getThisBTree().setChild(node);
                else getThisBTree().getMinChild().add(node);
                break;
            case 1:
                if (getThisBTree().getLeftChild() == null) {
                    BTree<T> newNode = new BTree<T>(getThisBTree(), node, node.getChildren());
                    getThisBTree().setNode(node, getThisBTree().getParent(), getThisBTree().getChildren());
                    getThisBTree().setChild(newNode);
                } else if (getThisBTree().getRightChild() == null) getThisBTree().setChild(node);
                else getThisBTree().getRightChild().add(node);
                break;
        }


        switch (this.depthOffset(getThisBTree()) == 0 ? 0
                : (0 < this.depthOffset(getThisBTree()) ? 1 : -1)) {
            case -1: this.rotateLeft(getThisBTree()); break;
            case 1: this.rotateRight(getThisBTree()); break;
            default: break;
        }


        super.getThisBTree().findDepth();
    }
}
