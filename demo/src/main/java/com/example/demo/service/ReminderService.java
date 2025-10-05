package com.example.demo.service;

import com.example.demo.repository.PawnContractRepository;
import com.example.demo.util.EmailUtil;
import com.example.demo.dto.PawnContractDto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReminderService {
    private final PawnContractRepository repository = new PawnContractRepository();

    public void sendDueDateReminders() {
        // Lấy hợp đồng còn 3 ngày hoặc ít hơn đến hạn
        List<PawnContractDto> contracts = repository.findContractsNearDueDate(7);

        for (PawnContractDto c : contracts) {
            if (c.getCustomerEmail() == null) continue;

            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), c.getDueDate());
            String subject = "⏰ Nhắc nhở chuộc đồ - Hợp đồng #" + c.getPawnContractId();
            String content = String.format(
                    "Chào %s,\n\nHợp đồng cầm đồ #%d cho sản phẩm %s sẽ đến hạn vào ngày %s (%d ngày nữa).\n" +
                            "Vui lòng đến cửa hàng để chuộc lại sản phẩm hoặc gia hạn hợp đồng.\n\nTrân trọng,\nTiệm Cầm Đồ",
                    c.getCustomerName(),
                    c.getPawnContractId(),
                    c.getProductName(),
                    c.getDueDate(),
                    daysLeft
            );

            EmailUtil.sendEmail(c.getCustomerEmail(), subject, content);
            System.out.println("📧 Gửi mail cho: " + c.getCustomerEmail());
        }

        System.out.println("✅ Reminder job completed at " + LocalDate.now());
    }

}
