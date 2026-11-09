import com.sap.gateway.ip.core.customdev.util.Message
import javax.activation.DataHandler
import javax.mail.util.ByteArrayDataSource

def Message processData(Message message) {
    byte[] fileBytes = message.getBody(byte[])
    
    ByteArrayDataSource dataSource = new ByteArrayDataSource(fileBytes, "application/pdf")
    DataHandler dataHandler = new DataHandler(dataSource)
    
    // Access attachments map directly
    Map<String, DataHandler> attachments = message.getAttachments()
    attachments.put("myAttachment.pdf", dataHandler)
    message.setAttachments(attachments)
    
    message.setBody("Hello,\n\nPlease find the attached document.\n\nRegards,\nSAP CPI")
    return message
}