package a4;

public interface IObservable {
public void addObserver(IObserver observer);
//Both mapview and scoreview but mapview is for next assignment
public void notifyObservers();
}
