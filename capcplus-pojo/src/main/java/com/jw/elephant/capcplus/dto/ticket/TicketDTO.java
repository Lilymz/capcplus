package com.jw.elephant.capcplus.dto.ticket;

import com.jw.elephant.capcplus.constant.Assert;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.pojo.Ticket;
import com.jw.elephant.capcplus.pojo.enums.TicketTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改开票信息DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    /**
     * id
     */
    private String idStr;
    /**
     * 发票抬头
     */
    private String ticketMan;
    /**
     * 税务登记号
     */
    private String creditCode;
    /**
     * 开户行名称
     */
    private String bank;
    /**
     * 开户行账号
     */
    private String bankAccount;
    /**
     * 营业电话
     */
    private String businessPhone;
    /**
     * 营业地址
     */
    private String businessAddress;
    /**
     *  纳税人资格类型
     */
    private String typeEnum;
    /**
     * 营业执照
     */
    private String businessLicense;
    /**
     * 一般纳税人资格认证扫描件
     */
    private String qualification;
    /**
     * 其他文件
     */
    private String otherFile;
    /**
     * 备注
     */
    private String remark;
    public static void check(TicketDTO ticketDTO){
        // 处理图片路径
        ticketDTO.setBusinessLicense(ticketDTO.getBusinessLicense().replaceAll(TextConstant.IMG_FOLDER,""));
        ticketDTO.setBusinessLicense(ticketDTO.getBusinessLicense().replaceAll(TextConstant.IMG_FOLDER,""));
        ticketDTO.setBusinessLicense(ticketDTO.getBusinessLicense().replaceAll(TextConstant.IMG_FOLDER,""));
        Assert.isBlank(ticketDTO.getTicketMan(),"请填写发票抬头！");
        Assert.isBlank(ticketDTO.getCreditCode(),"请填写统一社会信用代码/税务登记号！");
        Assert.isBlank(ticketDTO.getBank(),"请填写开户行名称！");
        Assert.isBlank(ticketDTO.getBankAccount(),"请填写开户行账号！");
        Assert.isBlank(ticketDTO.getBusinessPhone(),"请填写营业电话！");
        Assert.isBlank(ticketDTO.getBusinessAddress(),"请填写营业地址！");
        Assert.isBlank(ticketDTO.getTypeEnum(),"请填写纳税人资格类型！");
        Assert.isBlank(ticketDTO.getBusinessLicense(),"请填写营业执照/税务登记证扫描件！");
    }

    /**
     * 数据是否变化（无变化给前端友好提示） 准则:前端传的11条表单数据相同
     * @param currentDO
     * @param ticketDTO
     */
    public static void isChange(Ticket currentDO, TicketDTO ticketDTO) {
        int noChangeCount = 0;
        if (currentDO.getTicketMan().equals(ticketDTO.getTicketMan().trim())){
            noChangeCount++;
        }
        if (currentDO.getCreditCode().equals(ticketDTO.getCreditCode().trim())){
            noChangeCount++;
        }
        if (currentDO.getBank().equals(ticketDTO.getBank().trim())){
            noChangeCount++;
        }
        if (currentDO.getBankAccount().equals(ticketDTO.getBankAccount().trim())){
            noChangeCount++;
        }
        if (currentDO.getBusinessPhone().equals(ticketDTO.getBusinessPhone().trim())){
            noChangeCount++;
        }
        if (currentDO.getBusinessAddress().equals(ticketDTO.getBusinessAddress().trim())){
            noChangeCount++;
        }
        if (currentDO.getType().equals(TicketTypeEnum.valueOf(ticketDTO.getTypeEnum()))){
            noChangeCount++;
        }
        if (currentDO.getBusinessLicense().equals(ticketDTO.getBusinessLicense().trim())){
            noChangeCount++;
        }if (currentDO.getQualification().equals(ticketDTO.getQualification().trim())){
            noChangeCount++;
        }
        if (currentDO.getOtherFile().equals(ticketDTO.getOtherFile().trim())){
            noChangeCount++;
        }
        if (currentDO.getRemark().equals(ticketDTO.getRemark().trim())){
            noChangeCount++;
        }
        Assert.isTrue(noChangeCount ==11,"当前数据未变动~");
    }
}
