import React, { useState } from 'react'; 

import axios from 'axios';

function Login() {
    const [cpf, setCpf] = useState(''); 
    const [senha, setSenha] = useState(''); 

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8088/login', {
                cpf: cpf,
                senha: senha
            });

            const token = response.data.token;
            console.log("Token recebido:", token);
        } catch (error) {
            console.log("Alguma coisa deu errado", error);
        }
    }

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Cpf"
                    value={cpf}
                    onChange={(e) => setCpf(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Senha"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                />
                <button type="submit">Entrar</button>
            </form>
        </div>
    )
}

export default Login;
