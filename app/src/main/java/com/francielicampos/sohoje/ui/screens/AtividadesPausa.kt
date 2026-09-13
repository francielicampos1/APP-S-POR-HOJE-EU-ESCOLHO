package com.francielicampos.sohoje.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.francielicampos.sohoje.data.AppState
import com.francielicampos.sohoje.util.abrirUrl
import com.francielicampos.sohoje.util.abrirWhatsApp
import kotlinx.coroutines.delay

enum class AtividadePausa(val titulo: String, val emoji: String) {
    RESPIRAR("Respirar", "🫁"),
    BEBER_AGUA("Beber água", "🥤"),
    MUDAR_AMBIENTE("Mudar de ambiente", "🌳"),
    LAVAR_ROSTO("Lavar o rosto", "💧"),
    MUSICA("Colocar uma música calma", "🎵"),
    AFASTAR_CELULAR("Afastar o celular", "📵"),
    FALAR_COM_ALGUEM("Falar com alguém", "🤝"),
    TRES_COISAS_BOAS("3 coisas boas agora", "🌱")
}

@Composable
fun TelaEscolherAtividade(aoEscolher: (AtividadePausa) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text("Escolha uma ação", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text(
            "Cada uma leva só alguns minutos.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(Modifier.height(16.dp))
        AtividadePausa.entries.forEach { atividade ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = { aoEscolher(atividade) }
            ) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(atividade.emoji, style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.width(12.dp))
                    Text(atividade.titulo, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun TelaRespirar(aoContinuar: () -> Unit) {
    var fase by remember { mutableStateOf(0) } // 0=inspira 1=segura 2=solta
    val duracoes = listOf(4000, 2000, 6000)
    val textos = listOf("INSPIRA", "SEGURA", "SOLTA DEVAGAR")
    var repeticoes by remember { mutableIntStateOf(0) }

    val escala = remember { Animatable(0.6f) }
    LaunchedEffect(Unit) {
        while (repeticoes < 4) {
            fase = 0
            escala.animateTo(1f, tween(duracoes[0]))
            fase = 1
            delay(duracoes[1].toLong())
            fase = 2
            escala.animateTo(0.6f, tween(duracoes[2]))
            repeticoes++
        }
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Respire comigo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text(
            "Vamos diminuir o ritmo por alguns instantes.\nVocê não precisa resolver nada agora.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(40.dp))
        Box(
            Modifier
                .size((160 * escala.value).dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Text(textos[fase], style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
        }
        Spacer(Modifier.height(48.dp))
        Button(onClick = aoContinuar, enabled = repeticoes >= 2, modifier = Modifier.fillMaxWidth()) {
            Text(if (repeticoes >= 2) "Como você está agora?" else "Continue respirando…")
        }
    }
}

@Composable
fun TelaBeberAgua(aoContinuar: () -> Unit) {
    var segundosRestantes by remember { mutableIntStateOf(40) }
    LaunchedEffect(Unit) {
        while (segundosRestantes > 0) {
            delay(1000)
            segundosRestantes--
        }
    }

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Um pequeno intervalo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text("Pegue um copo de água.\nBeba devagar.\nEnquanto isso, deixe o celular de lado.", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(12.dp))
        Text(
            "Beber um copo de água com calma leva cerca de 40 segundos — tempo suficiente pra sua atenção começar a mudar de foco.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(Modifier.height(32.dp))
        if (segundosRestantes > 0) {
            Text("00:%02d".format(segundosRestantes), style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
        } else {
            Text(
                "Você acabou de criar alguns minutos entre a vontade e a ação.",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(20.dp))
            Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("CONTINUAR") }
        }
    }
}

@Composable
fun TelaMudarAmbiente(aoContinuar: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Mude de lugar", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text(
            "Levante-se e saia por alguns minutos do lugar onde você está. Vá até outro cômodo, caminhe pelo quintal, pela rua ou por um lugar seguro.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Enquanto caminha, não tente resolver sua vida. Só mude de lugar.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(Modifier.height(32.dp))
        Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("VOLTE QUANDO TERMINAR") }
    }
}

@Composable
fun TelaLavarRosto(aoContinuar: () -> Unit) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Interrompa o automático", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text(
            "Vá até a pia e lave o rosto com água fresca. Preste atenção na temperatura da água, na respiração e nas sensações do seu corpo.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(16.dp))
        Text("Volte sua atenção para o presente.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        Spacer(Modifier.height(32.dp))
        Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("ESTOU DE VOLTA") }
    }
}

@Composable
fun TelaMusica(aoContinuar: () -> Unit) {
    var comecou by remember { mutableStateOf(false) }
    var acabou by remember { mutableStateOf(false) }
    if (comecou) {
        LaunchedEffect(Unit) { delay(60000); acabou = true }
    }

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Escolha desacelerar", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text(
            "Coloque uma música que você gosta e que ajuda você a ficar mais tranquilo(a). Durante essa música, não abra aplicativos ou sites de apostas.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(32.dp))
        when {
            acabou -> {
                Text("A música acabou. E a vontade?", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(20.dp))
                Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("CONTINUAR") }
            }
            comecou -> {
                CircularProgressIndicator()
                Spacer(Modifier.height(12.dp))
                Text("Aproveite a música…", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(20.dp))
                TextButton(onClick = { acabou = true }) { Text("Já terminei") }
            }
            else -> Button(onClick = { comecou = true }, modifier = Modifier.fillMaxWidth()) { Text("COMEÇAR PAUSA") }
        }
    }
}

@Composable
fun TelaAfastarCelular(aoContinuar: () -> Unit) {
    var segundosRestantes by remember { mutableIntStateOf(300) }
    var iniciado by remember { mutableStateOf(false) }
    LaunchedEffect(iniciado) {
        if (iniciado) {
            while (segundosRestantes > 0) {
                delay(1000)
                segundosRestantes--
            }
        }
    }

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Crie distância", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text(
            "Coloque o celular longe de você por alguns minutos. Se possível, saia do aplicativo ou site que despertou sua vontade. Você não precisa tomar nenhuma decisão agora.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(28.dp))
        if (!iniciado) {
            Button(onClick = { iniciado = true }, modifier = Modifier.fillMaxWidth()) { Text("INICIAR 5 MINUTOS") }
        } else if (segundosRestantes > 0) {
            Text("%02d:%02d".format(segundosRestantes / 60, segundosRestantes % 60), style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text("Seu único objetivo é esperar.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        } else {
            Text("Você esperou. Isso já criou espaço entre a vontade e a ação.", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(20.dp))
            Button(onClick = aoContinuar, modifier = Modifier.fillMaxWidth()) { Text("CONTINUAR") }
        }
    }
}

@Composable
fun TelaFalarComAlguem(appState: AppState, aoContinuar: () -> Unit) {
    val context = LocalContext.current
    val contato = appState.contatosConfianca.firstOrNull()
    val mensagem = "Estou com vontade de apostar. Pode ficar comigo um pouco?"

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Você não precisa explicar tudo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        if (contato != null) {
            Text("Pode simplesmente dizer, pra ${contato.nome}:", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Text("\"$mensagem\"", Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { abrirWhatsApp(context, contato.telefone, mensagem) }, modifier = Modifier.fillMaxWidth()) {
                Text("ENVIAR MENSAGEM")
            }
        } else {
            Text(
                "Você ainda não cadastrou uma pessoa de confiança. Cadastre em Meu Plano → Minhas formas de me proteger.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(16.dp))
        Text("Ou fale com o CVV agora — gratuito e sigiloso, 24h:", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(10.dp))
        OutlinedButton(onClick = { abrirUrl(context, "https://www.cvv.org.br") }, modifier = Modifier.fillMaxWidth()) {
            Text("ACESSAR CVV")
        }
        Spacer(Modifier.height(20.dp))
        TextButton(onClick = aoContinuar) { Text("Continuar") }
    }
}

@Composable
fun TelaTresCoisasBoas(aoContinuar: () -> Unit) {
    var coisa1 by remember { mutableStateOf("") }
    var coisa2 by remember { mutableStateOf("") }
    var coisa3 by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        Text("🌱 3 coisas boas agora", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(
            "Escreva rapidinho 3 coisas pelas quais você é grata neste momento. Não precisam ser grandes.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = coisa1,
            onValueChange = { coisa1 = it },
            label = { Text("1.") },
            placeholder = { Text("Uma coisa boa...") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = coisa2,
            onValueChange = { coisa2 = it },
            label = { Text("2.") },
            placeholder = { Text("Outra coisa boa...") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = coisa3,
            onValueChange = { coisa3 = it },
            label = { Text("3.") },
            placeholder = { Text("Mais uma coisa boa...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = aoContinuar,
            enabled = coisa1.isNotBlank() || coisa2.isNotBlank() || coisa3.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Como você está agora?")
        }
    }
}
