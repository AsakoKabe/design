package com.example.provider.model.transfer;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    private final TransferDAO transferDAO = new TransferDAO();


    public Transfer findTransfer(UUID id) {
        return transferDAO.findById(id);
    }

    public void saveTransfer(Transfer transfer) {
        transferDAO.save(transfer);
    }

    public void deleteTransfer(Transfer transfer) {
        transferDAO.delete(transfer);
    }

    public void updateTransfer(Transfer transfer) {
        transferDAO.update(transfer);
    }

    public List<Transfer> findAllTransfers() {
        return transferDAO.findAll();
    }

    public Transfer getLastByTimestamp() {
        //                .toList()
        return transferDAO
                .findAll()
                .stream().max(Comparator.comparing(Transfer::getTimestamp))
                .orElse(null);
//                .get(0);
//                .getLast();
    }

}
