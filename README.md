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
    A[Kubernetes Certificate Authority (CA)]
    B[Certificate Signing Request (CSR)]
    C[Certificate]
    D[Private Key]
    E[CA Key]
    F[CA Certificate]

    A -->|Signs| B
    B --> C
    C -->|Stored in| D

    subgraph User Setup
        john[John]
        john.tag[Private Key]
        john.csr[CSR]
        john.cert[Certificate]

        john --> john.tag
        john --> john.csr
        john.csr -->|Signed by CA| john.cert
    end

    subgraph Kubernetes Config
        kubeconfig[/etc/kubernetes/pki]
    end



graph TD
    A[Master Node]

    subgraph Namespaces
        ns1[default]
        ns2[finance]
        ns3[kube-system]
    end

    role[Role]
    rolebinding[Role Binding]
    finance[finance]

    group[Group]

    john[John]
    thomas[Thomas]
    chris[Chris]

    role --> rolebinding
    rolebinding --> finance
    finance --> group

    group --> john
    group --> thomas
    group --> chris
