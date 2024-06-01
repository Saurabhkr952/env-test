# Kubernetes RBAC

## Concepts
- **Role**
- **ClusterRole**
- **RoleBinding**
- **ClusterRoleBinding**

## Explanation
When we create users/groups in Kubernetes, it is not aware of any concepts like users/groups. It only cares about certificates during the authentication. If we provide the proper certificate, you will be allowed access into the cluster.

## User Setup

```mermaid
graph TD
    A(Kubernetes Certificate Authority (CA)) -->|Signs| B(Certificate Signing Request (CSR))
    B --> C(Certificate)
    C -->|Stored in| D(Private Key)
    
    subgraph User Setup
        john(John) --> john_tag(Private Key)
        john --> john_csr(CSR)
        john_csr -->|Signed by CA| john_cert(Certificate)
    end
    
    subgraph Kubernetes Config
        kubeconfig(/etc/kubernetes/pki)
    end
