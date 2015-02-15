package structures;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 31, 2010
 * Time: 3:54:57 PM
 */
public class BTreeVertical<T extends Comparable<T>> extends BTreeBase<T> implements iBTree<T> {

    protected BTreeVertical() {}

    protected BTreeVertical(BTree<T> tree) { super(tree); }

    public BTree<T> join(BTree<T> tbTree) {
        return null;
    }

    public void rotateRight(BTree<T> node)
    {
        if(node == null || node.numberOfChildren() < 1) return;

        BTree<T> newNode = new BTree<T>(node, node.getParent(), null);
        node.setNode(node.getLeftChild(), node.getParent(), node.getChildren());
        node.getLeftChild().setNode(node.getLeftChild().swimUp(), node);

        if(node.numberOfChildren() < 2) node.setChild(newNode);
        else node.getRightChild().add(newNode);
    }

    public BTree<T> getMaxNode(BTree<T> node) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public BTree<T> getMinNode(BTree<T> node) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void rotateLeft(BTree<T> node)
    {
        if(node == null || node.numberOfChildren() < 2) return;

        BTree<T> newNode = new BTree<T>(node, node.getParent(), null);
        node.setNode(node.getRightChild(), node.getParent(), node.getChildren());
        node.getRightChild().setNode(node.getRightChild().swimUp(), node);

        node.getLeftChild().add(newNode);
    }

    public BTree<T> removeNode() {
        if(super.getThisBTree() == null) return null;

        BTree<T> removedNode = new BTree<T>(super.getThisBTree());
        super.getThisBTree().setNode(super.getThisBTree().swimUp());
        super.getThisBTree().setParent(removedNode.getParent());
        super.getThisBTree().fixParentDepths();
        if(super.getThisBTree().getParent() != null) super.getThisBTree().getParent().checkSwapSiblings();

        removedNode.setParent(null); removedNode.setDepth(0);
        removedNode.getChildren().removeAll(removedNode.getChildren());
        return removedNode;
    }

    public BTree<T> removeNode(BTree<T> node) {
        if(node == null || node.item == null) return null;

        BTree<T> removedNode = new BTree<T>(node);
        node.setNode(node.swimUp());

        node.setParent(removedNode.getParent());
        node.fixParentDepths();
        if(node.getParent() != null) node.getParent().checkSwapSiblings();

        removedNode.setParent(null); removedNode.setDepth(0);
        removedNode.getChildren().removeAll(removedNode.getChildren());

        return removedNode;
    }

    public void checkSwapSiblings() {

        if(super.getThisBTree().numberOfChildren() < 2) return;
        if(super.getThisBTree().getChildren().get(BTree.getLeftIndex())
                .compareTo(super.getThisBTree().getChildren().get(BTree.getRightIndex())) < 0)
            super.getThisBTree().swapSibling(BTree.getLeftIndex(), BTree.getRightIndex());
        }

    public BTree<T> swimUp(BTree<T> node) {

        if(node == null) return null;

        BTree<T> newNode;
        if((newNode = super.getThisBTree().getLeftChild()) == null) return null;
        node.removeChild(newNode);
        newNode.setParent(node.getParent());
        node.setNode(node.swimUp(node));
        node.removeChild(newNode);
        node.swimUp(newNode);

        return newNode;
        }

    public BTree<T> swimUp() {

        BTree<T> newNode;
        if(super.getThisBTree() == null || (newNode = super.getThisBTree().getLeftChild()) == null) return null;
        BTree<T> otherNode = super.getThisBTree().getRightChild();
        super.getThisBTree().removeChild(otherNode);
        newNode.swimUp();
        newNode.setChild(otherNode);
        newNode.checkSwapSiblings();

        return newNode;
        }

    public void add(BTree<T> node)
    {
        if(node == null) return;
        if(super.getThisBTree().getItem() == null) { super.getThisBTree().setItem(node.getItem()); return; }
        
        if(super.getThisBTree().compareTo(node) < 0)
        {
            BTree<T> newNode;
            newNode = new BTree<T>(super.getThisBTree(), super.getThisBTree(), node.getChildren());
            super.getThisBTree().setItem(node.getItem());
            super.getThisBTree().add(newNode);
        }
        else
        if(super.getThisBTree().getRightChild() == null)
            super.getThisBTree().setChild(node);
        else super.getThisBTree().getMinChild().add(node);
        super.getThisBTree().checkSwapSiblings();
        
        super.getThisBTree().findDepth();
    }
}
