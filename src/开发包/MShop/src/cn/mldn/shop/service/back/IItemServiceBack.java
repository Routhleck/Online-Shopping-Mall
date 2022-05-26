package cn.mldn.shop.service.back;

import cn.mldn.shop.vo.Item;

import java.util.List;
import java.util.Set;

public interface IItemServiceBack {
    public boolean insert(Item vo) throws Exception ;
    public boolean update(Item vo) throws Exception ;
    public boolean delete(Set<Integer> ids) throws Exception ;
    public List<Item> list() throws Exception ;
}
