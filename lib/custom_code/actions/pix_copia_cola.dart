/// Gera o código "Pix Copia e Cola" (padrão BR Code / EMV do Banco Central)
/// a partir de uma chave Pix — pra colar direto no clipboard, sem nunca
/// precisar mostrar a chave (CPF, e-mail, telefone etc.) em texto na tela.
///
/// Valor livre (a pessoa que doa escolhe quanto), sem valor fixo.
library pix_copia_cola;

String _tlv(String id, String value) {
  final len = value.length.toString().padLeft(2, '0');
  return '$id$len$value';
}

/// CRC16/CCITT-FALSE — o checksum exigido no final do código Pix.
int _crc16(String payload) {
  int crc = 0xFFFF;
  for (int i = 0; i < payload.length; i++) {
    crc ^= (payload.codeUnitAt(i) << 8);
    for (int j = 0; j < 8; j++) {
      if ((crc & 0x8000) != 0) {
        crc = ((crc << 1) ^ 0x1021) & 0xFFFF;
      } else {
        crc = (crc << 1) & 0xFFFF;
      }
    }
  }
  return crc & 0xFFFF;
}

/// [pixKey] pode ser CPF/CNPJ (só números), e-mail, telefone (+55DDDNUMERO)
/// ou chave aleatória. [merchantName] e [merchantCity] são só rótulos
/// (o banco de quem paga mostra o nome de verdade do dono da chave,
/// isso não dá pra mudar). Nada disso aparece em tela — só vira o código.
String gerarPixCopiaECola({
  required String pixKey,
  String merchantName = 'PIX APOIO',
  String merchantCity = 'SAO PAULO',
}) {
  final merchantAccountInfo =
      _tlv('00', 'br.gov.bcb.pix') + _tlv('01', pixKey);

  final semCrc = _tlv('00', '01') +
      _tlv('26', merchantAccountInfo) +
      _tlv('52', '0000') +
      _tlv('53', '986') +
      _tlv('58', 'BR') +
      _tlv('59', merchantName) +
      _tlv('60', merchantCity) +
      _tlv('62', _tlv('05', '***')) +
      '6304';

  final crc = _crc16(semCrc).toRadixString(16).toUpperCase().padLeft(4, '0');
  return semCrc + crc;
}
