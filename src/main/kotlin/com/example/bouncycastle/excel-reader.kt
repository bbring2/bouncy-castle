package com.example.bouncycastle

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.bouncycastle.crypto.params.KeyParameter
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream
import java.security.Security

@Component
class FileReader {

    fun read(path: String, password: String) {
        Security.addProvider(BouncyCastleProvider())

        // 비밀번호를 사용하여 키 생성
        val keyGen = PKCS5S2ParametersGenerator(SHA256Digest())
        keyGen.init(password.toByteArray(), "salt".toByteArray(), 65536)
        val key = (keyGen.generateDerivedParameters(256) as KeyParameter).key

        // Excel 파일 열기 및 비밀번호로 열기
        val file = FileInputStream(File(path))
        val workbook = WorkbookFactory.create(file, password)
        val sheet = workbook.getSheetAt(0)

        //파일 처리는 자유롭게 진행하시면 됩니다!!

        // 파일 닫기
        workbook.close()
        file.close()
    }
}