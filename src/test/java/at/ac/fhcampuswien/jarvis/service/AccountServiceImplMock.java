package at.ac.fhcampuswien.jarvis.service;

public class AccountServiceImplMock extends AccountServiceImpl {

    public AccountServiceImplMock() {
        super(new AccountRepositoryMock());
    }
}
