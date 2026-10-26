
abstract class Payment {

    abstract void Pay();
}

class Cash extends Payment{

    @Override
    void Pay() {
        
    }
    
}

class EWallet extends Payment{

    @Override
    void Pay() {
        
    }
    
}
