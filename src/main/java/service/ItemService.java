package service;

import dao.ItemDAO;
import objects.Item;

import java.io.IOException;
import java.util.Date;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public Item save(Item item) throws IOException{
        if (item == null)
            throw new IOException("Save failed.");
        if (itemDAO.findById(item.getId()) != null)
            throw new IOException("Item with id "+item.getId()+" is already exist");
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        return itemDAO.save(item);
    }

    public Item update(Item item) throws IOException{
        if (item == null)
            throw new IOException("Update failed.");
        if (itemDAO.findById(item.getId()) == null)
            throw new IOException("Item with id "+item.getId()+" not in DB");
        item.setLastUpdatedDate(new Date());
        return itemDAO.update(item);
    }

    public void delete(long id){
        itemDAO.delete(id);
    }
}
