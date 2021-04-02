package day11.homwork.test3;

import java.io.File;

public class Recepionist {
    private Filter filter=null;

    public Recepionist(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    void setUserLevel(Users users) {
        filter.filterUser(users);
    }
}
