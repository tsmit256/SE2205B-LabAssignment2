public class SinglyLinkedList<E>{

  private static class Node<E>{
    public E element;
    public Node<E> next = null;

    public Node(E e, Node<E> n){
      element = e;
      next = n;
    }
    public E getElement(){
      return element;
    }
    public Node<E> getNext(){
      return next;
    }
    public void setNext(Node<E> n){
      next = n;
    }
  }

  private int size;
  private Node<E> head;
  private Node<E> tail;

  public SinglyLinkedList(){
    size = 0;
    head = null;
    tail = null;
  }
  
  public int size(){
    return size;
  }
  
  public boolean isEmpty(){
    return size == 0;
  }
  
  public E first(){
    return head.getElement();
  }
  
  public E last(){
    return  tail.getElement();
  }
  
  public void addFirst(E element){
    head = new Node<E>(element,head);
    if(isEmpty()){
      tail = head;
    }
    size++;
  }
  
  public void addLast(E element){
    if(isEmpty()){
      addFirst(element);
    }
    else {
      tail.setNext(new Node<E>(element, null));
      tail = tail.getNext();
      size++;
    }
  }
  
  public E removeFirst(){
    if(isEmpty()){
      return null;
    }
    E retVal = head.getElement();
    head = head.getNext();
    size--;
    if(size == 0){
      tail = null;
    }
    return retVal;
  }

}