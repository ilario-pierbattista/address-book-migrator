package controller

import view.AddressBookSelector
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import javax.swing.JFileChooser
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val c = MainController()
}

class MainController {
    private val view: AddressBookSelector = AddressBookSelector()
    private var input: File? = null
    private var output: File? = null

    init {
        // Se la proprietà è null, lancia un NPE
        view.cancelButton!!.addActionListener({ _: ActionEvent ->
            AddressBookSelector.getFrame().dispose()
            exitProcess(0)
        })

        view.openBrowserButton!!.addActionListener { _: ActionEvent ->
            val fc = JFileChooser()
            when (fc.showOpenDialog(view.panel)) {
                JFileChooser.APPROVE_OPTION -> {
                    input = fc.selectedFile.absoluteFile
                    view.inputPathTextField.text = input.toString()
                    // change triggered
                    for (a: ActionListener in view.inputPathTextField.actionListeners) {
                        a.actionPerformed(ActionEvent(this, ActionEvent.ACTION_PERFORMED, null))
                    }
                }
            }
        }

        view.saveBrowseButton!!.addActionListener { _: ActionEvent ->
            val fc = JFileChooser()
            when (fc.showSaveDialog(view.panel)) {
                JFileChooser.APPROVE_OPTION -> {
                    output = fc.selectedFile.absoluteFile
                    view.outputNameTextField.text = output.toString()
                    // change triggered
                    for (a: ActionListener in view.outputNameTextField.actionListeners) {
                        a.actionPerformed(ActionEvent(this, ActionEvent.ACTION_PERFORMED, null))
                    }
                }
            }
        }

        view.inputPathTextField.addActionListener({ _: ActionEvent ->
            input = File(view.inputPathTextField.text)
            when (input?.exists()) {
                true -> view.setNonExistentFileError(false)
                else -> {
                    view.setNonExistentFileError(true)
                    input = null
                }
            }
            checkPreconditions()
        })

        view.outputNameTextField.addActionListener { _: ActionEvent ->
            output = File(view.outputNameTextField.text)
            when (output?.parentFile?.exists()) {
                true -> view.setNonExistentFolderError(false)
                else -> {
                    view.setNonExistentFolderError(true)
                    output = null
                }
            }
            checkPreconditions()
        }

        view.okButton.addActionListener { _: ActionEvent ->
            convert()
        }
    }

    fun convert() {
        val header: String = "First Name,Last Name,E-mail Display Name,Nickname,E-mail Address,E-mail 2 Address,Screen Name,Business Phone,Home Phone,Business Fax,Pager,Mobile Phone,Home Street,Home Street 2,Home City,Home State,Home Postal Code,Home Country/Region,Business Street,Business Street 2,Business City,Business State,Business Postal Code,Business Country/Region,Job Title,Department,Company,Web Page,Web Page 2,Birth Year,Birth Month,Birth Day,User 1,User 2,User 3,User 4,Notes"
        var changedHeader:Boolean = false
        val writer:PrintWriter = PrintWriter(FileWriter(output!!))

        input!!.forEachLine { l:String ->
            when(changedHeader) {
                false -> {
                    changedHeader = true
                    writer.println(header)
                }
                else -> {
                    writer.println(l)
                }
            }
        }
        writer.close()
        view.successMsg()
        input = null
        output = null
        checkPreconditions()
    }

    fun checkPreconditions() {
        view.okButton.isEnabled = (input != null && output != null)
    }
}